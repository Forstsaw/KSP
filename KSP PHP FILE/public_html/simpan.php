<?php 
	require 'conn.php';
	 $mysqltime = date("Y/m/d"); 
	 $userid = $_POST['user_id'];
     $balance = $_POST['balance'];
     $totalBalance= $_POST['totalBalance'];
   // $balance = 10000;
//	 $userid = 7;
	//$totalBalance = 160000;
//	$balance=mysqli_real_escape_string($conn,$_POST['balance']);
 	$query="INSERT into simpanan(user_id,balance,tanggal_simpan) values ('$userid','$balance','$mysqltime')";
    $result=mysqli_query($conn,$query) or die(mysqli_error($conn));

    $query2="UPDATE balance SET balance ='$totalBalance' WHERE user_id = '$userid'";
    $result2=mysqli_query($conn,$query2) or die(mysqli_error($conn));
    
    if($result && $result2) 
	{
		echo "success";
	}else{
		echo "failed";

	}

?>