#!/usr/bin/env groovy

pipelineJob('account-02') {
  definition {
    cps {
      script('''
        pipeline {
        parameters {
          choice(choices: ['ACCOUNT-02'], description: 'Name of the Account?', name: 'account_name')
        }
          agent any
            stages {
              stage ('Clone Repo') {
                steps {
                  checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '', url: 'https://github.com/jeyaramji/web01.git']]])
                  sh "echo 'Build phase - ${params.account_name}'"
                   writeFile file: "report.csv", text: "This file is useful, need to archive it."
                   writeFile file: "report.html", text: "This file is useful, need to archive it."
                }
              }
//              stage('Build') {
//                steps {
//                  sh 'pip3 install -r requirements.txt'
//                  }
              }
              stage ('Generate report') {
                steps {
                  sh "echo 'Build phase - ${params.account_name}'"
                   writeFile file: "report.csv", text: "This file is useful, need to archive it."
                   writeFile file: "report.html", text: "This file is useful, need to archive it."
                }
              }
              stage ('Save Report') {
                steps {
                  archiveArtifacts artifacts: '*.csv', excludes: 'output/*.md'
                  archiveArtifacts artifacts: '*.html', excludes: 'output/*.md'
                }
              }
            }
        }
      '''.stripIndent())
      sandbox()
    }
  }
}