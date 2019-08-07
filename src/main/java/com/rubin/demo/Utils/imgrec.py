# coding=utf-8

import base64
import requests
from sys import argv

#import sys
#传参

#图片转二进制
f = open(argv[1], 'rb')
#f = open('E:/学习软件/JAVA实训/图像识别/Python/test3.jpg', 'rb')
#fff = open('eee.txt', 'a+')
img = base64.b64encode(f.read())


#查询
host = 'https://aip.baidubce.com/rest/2.0/image-classify/v2/advanced_general'
headers={
   'Content-Type':'application/x-www-form-urlencoded'
}
access_token= '24.27a761933add9a4e6ee5ef779e0efd45.2592000.1565594715.282335-16796087'
host=host+'?access_token='+access_token

data={}
data['access_token']=access_token
data['image'] =img
res = requests.post(url=host,headers=headers,data=data)
req=res.json()
#print(req['result'])
print(req['result'][0]['keyword'])
