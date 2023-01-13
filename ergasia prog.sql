DROP TABLE allcomments;
DROP TABLE users;
DROP TABLE allposts;


CREATE TABLE users (
   username VARCHAR(30) not null primary key,
   upassword VARCHAR(20) not null,
   typeaccount int not null
)

CREATE TABLE allposts (
   postid int not null primary key auto increment,
   post VARCHAR(200),
   rating INT not null,
   likes INT not null,
   commentnumber INT not null,
   username VARCHAR(30) not null FOREIGN KEY REFERENCES users(username)
)

CREATE TABLE allcomments (
   commentid int not null primary key auto increment,
   comment TEXT not null,
   postid int not null FOREIGN KEY REFERENCES allposts(postid),
   username VARCHAR(30) not null FOREIGN KEY REFERENCES users(username)
)