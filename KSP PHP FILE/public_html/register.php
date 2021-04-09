<?php
    require 'conn.php';

    $name= mysqli_real_escape_string($conn,$_POST['name']);
    $username=mysqli_real_escape_string($conn,$_POST['username']);
    $password=mysqli_real_escape_string($conn,$_POST['password']);
    $confpass=mysqli_real_escape_string($conn,$_POST['confpass']);

    //$name= mysqli_real_escape_string($conn,"adssad");
    //$username=mysqli_real_escape_string($conn,"adasdasd");
    //$password=mysqli_real_escape_string($conn,"12321321321");
    //$confpass=mysqli_real_escape_string($conn,"12321321321");

    $duplicate_user_query="select id from user where username='$username'";
    $duplicate_user_result=mysqli_query($conn,$duplicate_user_query)or die(mysqli_error($conn));
    $rows_fetched=mysqli_num_rows($duplicate_user_result);
    if($rows_fetched>0){
        //duplicate registration
        //header('location: signup.php');
        echo "duplicate";
        
    }else{
        $user_registration_query="insert into user(name,username, password) values ('$name','$username','$password')";
        $user_registration_result=mysqli_query($conn,$user_registration_query) or die(mysqli_error($conn));
        $user_id =mysqli_insert_id($conn); 
        $user_balance="insert into balance(user_id,balance) values ('$user_id','0')";
        $balance_result=mysqli_query($conn,$user_balance) or die(mysqli_error($conn));
        echo "success";
       
    }
    
?>