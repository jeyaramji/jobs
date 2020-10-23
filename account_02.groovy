#!/usr/bin/env groovy

pipelineJob('example-pipeline') {
  definition {
    cps {
      script('''
        pipeline {
        parameters {
          choice(choices: ['ACCOUNT-02''], description: 'Name of the Account?', account_name: 'account02')
        }
          agent any
            stages {
              stage ('Build') {
                steps {
                  sh "echo 'Build phase - ${params.account_name}'
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