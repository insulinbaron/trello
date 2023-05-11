pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
    }
    parameters {
        choice(name: "tests", choices: "API\nUI", description: "Тесты для запуска")
        string(name: "branch", defaultValue: "main", description: "Имя ветки для запуска")
    }

    stages {
        stage('Build') {
            steps {
                 git(url: 'https://github.com/insulinbaron/trello.git', branch: '${branch}')
            }
        }

        stage('Compile') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run test') {
            steps{
                sh 'mvn test'
            }
        }
    }
}