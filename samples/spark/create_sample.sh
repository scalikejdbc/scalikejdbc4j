#!/bin/bash

curl -XPOST localhost:4567/companies -d'name=Typesafe'
curl -XPOST localhost:4567/companies -d'name=GitHub'

curl -XPOST localhost:4567/programmers -d'gitHubName=odersky&realName=Martin Odersky&companyId=1'

curl -XPOST localhost:4567/programmers -d'gitHubName=defunkt&companyId=2'
curl -XPUT localhost:4567/programmers/2 -d'gitHubName=defunkt&realName=Chris Wanstrath&companyId=2'

