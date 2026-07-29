pipeline {
    agent any

    tools {
        maven 'Maven3' // Matches the name you gave in Jenkins Tools
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Checking out source code from GitHub...'
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                echo 'Compiling code and running JUnit test suite...'
                bat 'mvn clean test'
            }
        }

        stage('Package App') {
            steps {
                echo 'Packaging application into executable JAR...'
                bat 'mvn package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                echo 'Building Docker container image for OrderService...'
                bat 'docker build -t devops-orderservice:latest .'
            }
        }
    }

    post {
        success {
            echo '====================================================='
            echo 'SUCCESS: CI Pipeline completed! Image ready for deployment.'
            echo '====================================================='
        }
        failure {
            echo 'CRITICAL: CI Pipeline build or testing failed!'
        }
    }
}