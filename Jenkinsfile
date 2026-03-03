pipeline {
    agent any

    triggers {
        githubPush()
    }

    environment {
        BUILD_DIR = "built"
        REPO_URL = "https://github.com/tamiron02/bank-account.git"
        BRANCH = "main"
        PROJECT_DIR = "bank-account"
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: "${BRANCH}",
                    credentialsId: 'jenkins-github-creds',
                    url: "${REPO_URL}"
            }
        }

        stage('Build and Test with Maven') {
    steps {
        sh 'mvn clean test'
    }
}


       stage('Organize Artifacts') {
            steps {
               
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

            archiveArtifacts artifacts: 'built/*.jar', fingerprint: true
        }
        failure {
            echo "Pipeline failed. Check the Jenkins console output to see if a JUnit test failed or if there was a compilation error."
        }
    }
}