# 概要
家事など日常生活での行動を記録することでポイントを貯めるアプリ。
自分にとって良い行いをすると経験値が貯まるが、スマホをだらだら見て過ごすなどの悪いことをすると経験値が減る。
悪い行いも含めて自分で記録することで日頃の行いを見つめなおす機会になり、経験値を貯めるために自堕落な生活も改善することができる。

# 主な使用技術
- Spring Boot
- Spring Security
- PostgreSQL 16

# セットアップ
### DB設定
~~~
$ cd src/main/resources/static/init 
~~~
PostgreSQLにログインする(例)
~~~
$ brew services start postgresql@16
$ psql -U postgresql
~~~
ユーザーとDB作成
~~~
$ \i init_db.sql
$ \q
~~~
作成したユーザーでログイン後、テーブル作成
~~~
$ psql hshdb -U hshuser
$ \i init_user_table.sql
$ \i init_todo_table.sql
$ \i init_housaholic_table.sql
$ \q
~~~

# 起動手順
* DB起動 : brew services start postgresql@16
* java起動　: java -jar target/housaholic-0.0.1-SNAPSHOT.jar

# 停止
brew services stop postgresql@16