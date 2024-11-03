pipeline {
    agent any  // Use the default Jenkins node

    tools {
        maven 'Maven 3.8.7'  // Set this to your Maven version in Jenkins
        jdk 'JDK 21'         // Adjust JDK version based on project requirements
    }

    environment {
        ALLURE_RESULTS = 'allure-results'  // Folder for Allure results
    }

    stages {
        stage('Checkout') {
            steps {
                // Clone the Git repository
                git url: 'https://github.com/MyAutomationLabs/LatestCucumberProject.git'
            }
        }

        stage('Build') {
            steps {
                // Run Maven clean and package
                script {
                    withMaven(maven: 'Maven 3.8.7') {
                        sh 'mvn clean package'
                    }
                }
            }
        }

        stage('Test') {
            steps {
                // Run TestNG tests with Maven
                script {
                    withMaven(maven: 'Maven 3.8.7') {
                        sh 'mvn test'
                    }
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true
            
            // Publish TestNG test results
           // junit allowEmptyResults: true, testResults: '**/test-results/*.xml'
            //junit testResults: '**/testng-results/*.xml'

            // Publish Allure report
            allure includeProperties: false, jdk: '', reportBuildPolicy: 'ALWAYS', results: [[path: "${env.ALLURE_RESULTS}"]]
        }
    }
}
