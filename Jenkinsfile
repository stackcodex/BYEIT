
pipeline {
    agent any

    options {
        timestamps()
    }

    triggers {
        cron('H 9 * * *')
    }

    stages {
        stage('Checkout from GitHub') {
            steps {
                checkout scm
                echo 'Source code downloaded from GitHub.'
            }
        }

        stage('Install Python Dependencies') {
            steps {
                bat 'python --version'
                bat 'python -m pip install -r python-tests\\requirements.txt'
            }
        }

        stage('Run Selenium Tests') {
            steps {
                script {
                    bat 'if not exist "python-tests\\reports" mkdir "python-tests\\reports"'

                    def result = bat(
                        returnStatus: true,
                        script: 'python -m pytest python-tests -v -s --junitxml=python-tests\\reports\\pytest-results.xml'
                    )

                    junit(
                        allowEmptyResults: true,
                        testResults: 'python-tests/reports/pytest-results.xml'
                    )

                    if (result != 0) {
                        error('One or more Python Selenium tests failed.')
                    }
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts(
                artifacts: 'python-tests/reports/pytest-results.xml',
                allowEmptyArchive: true
            )
        }

        failure {
            echo 'The pipeline failed. Gmail notification will be configured next.'
        }
    }
}
