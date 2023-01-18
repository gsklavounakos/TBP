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

delete from allposts where postid>1

insert into allposts values ('Έμεινα πολύ ευχαριστημένη με την επίσκεψη μου στο μαγαζί Πάστα. Η εξυπηρέτηση ήταν πολύ καλή και το φαγητό γευστικό. Ήταν όλα τέλεια.','5','30','0','man')
insert into allposts values ('Έμεινα πολύ ευχαριστημένη με την επίσκεψη μου στο μαγαζί "Το Λεμόνι". Ήταν όλα τέλεια.','5','30','0','man')
insert into allposts values ('Η εμπειρία μου στο μαγαζί "Ιταλικό παγωτό" ηταν τέλεια. Αν ψάχνετε καλό παγωτό είναι το κατάλληλο μέρος.','5','30','0','man')
insert into allposts values ('Πήγα εχθές στο γνωστό μαγαζί Fridays και έμεινα αρκετά ευχαριστημένη.Ήταν πολύ νόστιμο το πιάτο με τα μπιφτέκια τους.','5','30','0','man')
insert into allposts values ('Φανταστικό το μαγαζι "Σούβλες" αν θέλετε να φάτε παραδοσιακό κρητικο φαγητό.','5','30','0','man')
insert into allposts values ('Ένα απο τα καλύτερα μαγαζία για ποικιλία κρεατικών και μπύρες. Συνιστώ σε όλους το μαγαζί "Η μπύρα".','5','30','0','man')
