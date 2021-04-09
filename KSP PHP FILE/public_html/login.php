
<?php 
	require "conn.php";
	require "connection.php";
	$username = $_POST["username"];
	$password = $_POST["password"];
	//$username = "m";
	//$password = "m";
	/*
	$mysql_qry = "select * from user where username = '$username' and password = '$password'";
	$result = mysqli_query($conn ,$mysql_qry);
	$cek = mysqli_num_rows($result);
	$response = array();
	if(	$cek > 0) {
		$status = "success";
		$pesan = "Selamat datang";
		//echo json_encode(array_push($response,$status,$pesan));
		//print_r($response);
		echo json_encode($status);
	}
	else {
		$status = "failed";
		$pesan = "Gagal Login";
		echo $status;
	}*/

	$sql = "SELECT * FROM user
	WHERE username= ? and password = ?";

	$hasil = $kunci->prepare($sql);
	$hasil ->execute([$username,$password]);
	if ($hasil->rowCount() > 0) {
		$data = $hasil->fetch();
		echo "success,".$data['id'].",".$data['name'];
	}



	 
?>