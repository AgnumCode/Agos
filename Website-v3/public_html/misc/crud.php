<?php


//include_once 'db.php';

/*
//Request type switch
switch ($data.requestType) {
    case "GET":
        getPost($data);
        break;
    case "POST":
        createPost();
        break;
    case 2:
        null;
        break;
    default: 
        echo "Error.";
        break;
}

//GET request
/**************************************************/
/*
function getPost() {
$sel = mysqli_query($this.con, "SELECT * FROM `posts`");
$data = array();

while ($row = mysqli_fetch_array($sel)) {
 $data[] = array("postTitle"=>$row['postTitle'],
				  "postDate"=>$row['postDate'],
				   "postEnd"=>$row['postEnd'],
				   "content"=>$row['content'],
				"subPostUrl"=>$row['subPostUrl'],
				   "subPost"=>$row['subPost'],
                          "subPostTitleBody"=>$row['subPostTitleBody']);
}
echo json_encode($data);
}

//POST create post request
/**************************************************/
/*
function createPost($data) {

	mysqli_query($this.con, "INSERT INTO `posts` (`id`, 
                                                 `postTitle`, 
                                                 `postDate`, 
                                                 `postEnd`, 
                                                 `content`, 
                                                 `subPostUrl`, 
                                                 `subPost`,
                                                 `subPostTitleBody`) 
        VALUES ('', '".$data->postTitle."',"
                 . "'".date('Y-m-d H:i:s')."',"
                 . "'".$data->postEnd."',"
                 . "'".$data->content."',"
                 . "'".$data->subPostUrl."',"
                 . "'".$data->subPost."',"
                 . "'".$data->subPostTitleBody."')");
        echo json_encode($data);
} 