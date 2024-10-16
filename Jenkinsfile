pipeline {
    agent any

    tools {
        // Use Jenkins pre-configured tools
        maven 'Maven'  // Name of the Maven installation in Jenkins

    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from your Git repository
                git url: 'https://github.com/kasthuriChidambaram/UIAutomationWithPicoContainer.git', branch: 'main'
            }
        }

        stage('Run Tests') {
            steps {
                // Run the tests using Maven
                sh 'mvn test'
            }
        }

        stage('Publish Cucumber Report') {
            steps {
                // Generate Cucumber HTML reports
                cucumber fileIncludePattern: '**/target/cucumber/*.json'
            }
        }


    }

    post {
        always {
            // Clean the workspace after the build completes
            cleanWs()
        }

        failure {
            // Archive artifacts (logs or other files) if the build fails
            archiveArtifacts artifacts: '**/*.*', allowEmptyArchive: true
        }
    }
}
