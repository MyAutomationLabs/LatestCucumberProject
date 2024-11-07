pipeline {
    agent any  // Use the default Jenkins node

    tools {
        maven 'Maven 3.8.7'  // Set this to your Maven version in Jenkins
        jdk 'JDK 21'         // Adjust JDK version based on project requirements
    }

    environment {
        ALLURE_RESULTS = 'target/allure-results'  // Match the Allure results directory path
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
                sh 'mvn clean package'
            }
        }

        stage('Verify') {  // Renamed to align with 'verify' phase in POM
            steps {
                // Run Maven verify to execute tests and generate the Allure report
                sh 'mvn verify'
            }
        }

        stage('Debug Allure Results') {
            steps {
                // Verify Allure results directory contains results
                sh 'ls -l target/allure-results'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true
            
            // Publish Allure report
            allure includeProperties: false, jdk: '', reportBuildPolicy: 'ALWAYS', results: [[path: "${env.ALLURE_RESULTS}"]]
        }
    }
}
