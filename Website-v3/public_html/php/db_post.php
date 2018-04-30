<?php

require 'db_config.php';

//000webhost db credentials
//$host = "localhost";            /* Host name */
//$user = "id5161577_default";    /* User */
//$password = "novanova"; 	  /* Password */
//$dbname = "id5161577_posts";    /* Database name */

//date('Y-m-d H:i:s')

//Prepared statement for selecting all posts from table 'posts'
$stmt = $pdo->prepare("SELECT * FROM posts");
$stmt->execute();
$results = $stmt->fetchAll(PDO::FETCH_ASSOC);
$json = json_encode(array_reverse($results));

echo $json;


