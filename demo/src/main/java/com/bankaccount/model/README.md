pipeline {
    agent any

    triggers {
        // This listens to the GitHub Webhook coming through your ngrok tunnel
        githubPush()
    }

    environment {
        // The exact ID you created in Jenkins Credentials for your PAT
        CREDENTIALS_ID = 'github-token'
        // Be sure to swap out YOUR_USERNAME with your actual GitHub handle
        REPO_URL = 'https://github.com/YOUR_USERNAME/BankAccount.git'
        BRANCH = 'main'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: "${BRANCH}",
                        credentialsId: "${CREDENTIALS_ID}",
                        url: "${REPO_URL}"
            }
        }

        stage('Build and Test with Maven') {
            steps {
                // 'clean package' compiles the Java code, runs your JUnit 5 tests,
                // and packages the passing code into a JAR file.
                sh 'mvn clean package'
            }
        }

        stage('Organize Artifacts') {
            steps {
                // Maven automatically outputs to a 'target' directory.
                // This copies it into a 'built' directory just like your template.
                sh '''
                    mkdir -p built
                    cp target/*.jar built/
                '''
            }
        }
    }

    post {
        success {
            echo "Build and Tests successful! JAR stored in built/"
            // This makes the JAR file downloadable directly from the Jenkins dashboard
            archiveArtifacts artifacts: 'built/*.jar', fingerprint: true
        }
        failure {
            echo "Pipeline failed. Check the Jenkins console output to see if a JUnit test failed or if there was a compilation error."
        }
    }
}