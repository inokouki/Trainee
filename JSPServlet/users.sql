CREATE  TABLE `kadai4`.`users` (
 
 `id` INT NOT NULL AUTO_INCREMENT ,
 
 `loginid` VARCHAR(20) NOT NULL ,
 
 `password` VARCHAR(255) NOT NULL ,
 
 `name` VARCHAR(10) NOT NULL ,
 
 `branches_id` INT NOT NULL ,
 
 `departments_id` INT NOT NULL ,
 
 `available` TINYINT NOT NULL ,
  
PRIMARY KEY (`id`) ,
  
UNIQUE INDEX `loginid_UNIQUE` (`loginid` ASC) ,
  
UNIQUE INDEX `branches_id_UNIQUE` (`branches_id` ASC) ,
  
UNIQUE INDEX `departments_id_UNIQUE` (`departments_id` ASC) );