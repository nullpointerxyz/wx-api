docker build -t registry.cn-hangzhou.aliyuncs.com/songkaizong/wx-api .
docker push registry.cn-hangzhou.aliyuncs.com/songkaizong/wx-api
docker pull registry.cn-hangzhou.aliyuncs.com/songkaizong/wx-api
docker run -d -p