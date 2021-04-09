<?php 
	require 'conn.php';
	 $time_now = date("Y/m/d"); 
	 $time_now2 = strtotime($time_now);
	 $jatuh_tempo = date("Y/m/d", strtotime("+1 month", $time_now2));

	 $userid = $_POST['user_id'];
     $balance = $_POST['balance'];
     $status = "peminjaman";
   // $balance = 10000;
//	 $userid = 7;
	//$totalBalance = 160000;
//	$balance=mysqli_real_escape_string($conn,$_POST['balance']);
 	$query="INSERT into pinjaman(user_id,balance,waktu_peminjaman,jatuh_tempo,status) values ('$userid','$balance','$time_now','$jatuh_tempo','$status')";
    $result=mysqli_query($conn,$query) or die(mysqli_error($conn));


    if($result) 
	{
		echo "success";
	}else{
		echo "failed";

	}

?>