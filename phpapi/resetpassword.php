<?php
$response = array();
if($_SERVER['REQUEST_METHOD']=='POST'){
    require_once('connect.php');
    $password = $_POST['password'];
    $email = $_POST['email'];
    $insertQuery="UPDATE `register` SET `password`='$password' WHERE `email`='$email'";
    
    if(mysqli_query($conn,$insertQuery)){
          $response["message"] = "password change successfully";
    }
    else{
          $response["message"] = "Email Doesn't Exist";
    }
  }
 else{
 $response["message"] = 'Something Went wrong';
 }
echo json_encode($response);
?>
