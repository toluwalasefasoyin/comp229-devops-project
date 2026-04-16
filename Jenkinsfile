pipeline {
    agent any

    tools {
        maven 'MAVEN3'
    }

    triggers {
        pollSCM('H/5 * * * *')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo 'Running SonarQube static code analysis...'
                echo 'Mock SonarQube stage for demo'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
            post {
                always {
                    jacoco()
                }
            }
        }

        stage('Deliver') {
            steps {
                bat 'mvn package'
            }
        }

        stage('Deploy to Dev Env') {
            steps {
                echo 'Deploying to Dev environment...'
                bat 'echo App deployed to Dev'
            }
        }

        stage('Deploy to QAT Env') {
            steps {
                echo 'Deploying to QAT environment...'
                bat 'echo App deployed to QAT'
            }
        }

        stage('Deploy to Staging Env') {
            steps {
                echo 'Deploying to Staging environment...'
                bat 'echo App deployed to Staging'
            }
        }

        stage('Deploy to Production Env') {
            steps {
                echo 'Deploying to Production environment...'
                bat 'echo App deployed to Production'
            }
        }
    }
}      