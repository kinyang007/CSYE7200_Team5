## How to Run
### 1. Import data to MongoDB Atlas
```shell
$ python generate --help
usage: generate.py [-h] -n NAME -p PASSWORD

Generate Data to MongoDB Atlas

optional arguments:
  -h, --help            show this help message and exit
  -n NAME, --name NAME  MongoDB Atlas User Name
  -p PASSWORD, --password PASSWORD
                        MongoDB Atlas Password
```
### 2. Import data to MongoDB Localhost
```shell
$ python generate.py
```
### 3. Necessary packages
* pymongo
* pymongo[srv]