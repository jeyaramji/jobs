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
              stage ('Build') {
                steps {
                  sh "echo 'Build phase - ${params.account_name}'"
                   writeFile file: "report.csv", text: "This file is useful, need to archive it."
                   writeFile file: "report.html", text: "This file is useful, need to archive it."
                }
              }
              stage ('Unit tests') {
                steps {
                  echo 'Unit testing phase'
                }
              }
              stage ('Deploy') {
                steps {
                  echo 'Deploy phase'
                }
              }
            }
        }
      '''.stripIndent())
      sandbox()
    }
  }
}