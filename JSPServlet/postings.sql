CREATE  TABLE `kadai4`.`postings` (
 
 `id` INT NOT NULL AUTO_INCREMENT ,
 
 `title` VARCHAR(50) NOT NULL ,
 
 `text` TEXT NOT NULL ,
 
 `category` VARCHAR(10) NOT NULL ,
 
 `created` TIMESTAMP NULL ,
 
 `modified` TIMESTAMP NULL ,
  
 `users_name` VARCHAR(10) NOT NULL ;
PRIMARY KEY (`id`) );