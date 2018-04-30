<?php

//000webhost db credentials
//$host = "localhost";            /* Host name */
//$user = "id5161577_default";    /* User */
//$password = "novanova"; 	  /* Password */
//$dbname = "id5161577_posts";    /* Database name */

$host = 'localhost';
$db = 'posts';
$user = 'root';
$pass = '';

//Create data source name
$dsn = 'mysql: host=' . $host . ';dbname=' . $db;

//PDO Instance
$pdo = new PDO($dsn,$user,$pass);