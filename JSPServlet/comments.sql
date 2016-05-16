CREATE  TABLE `kadai4`.`comments` (
 
 `id` INT NOT NULL AUTO_INCREMENT ,
 
 `text` TEXT NOT NULL ,
 
 `created` TIMESTAMP NULL ,
 
 `modified` TIMESTAMP NULL ,
 
 `postings_id` INT NOT NULL ,
 
 PRIMARY KEY (`id`) ,
  
UNIQUE INDEX `postings_id_UNIQUE` (`postings_id` ASC) );