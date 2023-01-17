DROP TABLE allcomments;
DROP TABLE allusers;
DROP TABLE allposts;


CREATE TABLE allusers (
   username VARCHAR(30) not null primary key,
   upassword VARCHAR(20) not null,
   typeaccount int not null
)

CREATE TABLE allposts (
   postid int not null primary key identity(1,1),
   post VARCHAR(200),
   rating INT not null,
   likes INT not null,
   commentnumber INT not null,
   username VARCHAR(30) not null FOREIGN KEY REFERENCES allusers(username)
)

CREATE TABLE allcomments (
   commentid int not null primary key identity(1,1),
   comment TEXT not null,
   postid int not null FOREIGN KEY REFERENCES allposts(postid),
   username VARCHAR(30) not null FOREIGN KEY REFERENCES allusers(username)
)
