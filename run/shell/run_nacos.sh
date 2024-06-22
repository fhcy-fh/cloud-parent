#! /bin/bash

echo ">>> start"

cd /cloud/project/cloud-parent/

git_result=`git pull`
str="Already up to date."

if [ "$git_result" = "$str" ];then
    echo ">>> skip mvn install"
else
    echo ">>> mvn clean install"
    mvn clean install -Dspring.profiles.active=dev -Dmaven.test.skip=true
fi

echo ">>> stop container"

docker stop cloud-nacos

docker rm cloud-nacos
docker rmi cloud-nacos

cd /cloud/project/cloud-parent/cloud-visual/cloud-nacos/

docker build -t cloud-nacos .

cd /cloud/build/

docker-compose -f docker-nacos-compose.yml up -d

echo ">>> end"