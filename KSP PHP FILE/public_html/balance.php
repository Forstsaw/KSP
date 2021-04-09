
<?php 
	
	require "connection.php";
	$id = $_POST["id"];
//	$id = "7";

	$sql = "SELECT * FROM balance
	WHERE user_id= ?";

	$hasil = $kunci->prepare($sql);
	$hasil ->execute([$id]);

	$data = $hasil->fetch();
	echo "success,".$data['balance_id'].",".$data['balance'];




	 
?>