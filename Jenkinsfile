pipeline {
    agent any
    tools {
        maven 'apache-maven-3.8.4'
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
    }
    triggers {
        cron(env.BRANCH_NAME == 'main' ? 'H H/12 * * *' : "")
    }
    parameters {
        choice(name: "tests", choices: "ALL\nAPI\nUI", description: "Тесты для запуска")
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
                when {
                    allOf {
                        tests: ALL
                    }
                    sh 'mvn test -Dsurefire.suiteXmlFiles=testng.xml'
                }
                when {
                    allOf {
                        tests: API
                    }
                    sh 'mvn test -Dsurefire.suiteXmlFiles=testng-api.xml'
                }
                when {
                    allOf {
                        tests: UI
                    }
                    sh 'mvn test -Dsurefire.suiteXmlFiles=testng-api.xml'
                }

            }
        }
        stage('Report') {
                    steps {
                        allure([
                                includeProperties: false,
                                jdk              : '',
                                properties       : [],
                                reportBuildPolicy: 'ALWAYS',
                                results          : [[path: 'target/allure-results']]
                        ])
    }
}