
<?php 
	
	require "connection.php";
	$id = $_POST["user_id"];
	//$id = "7";

	$sql = "SELECT * FROM pinjaman
	WHERE user_id= ?";

	$hasil = $kunci->prepare($sql);
	$hasil ->execute([$id]);

	$data = $hasil->fetch();
	if ($hasil->rowCount() > 0) {
		echo "success,".$data['user_id'].",".$data['pinjam_id'].",".$data['balance'].",".$data['waktu_peminjaman'].",".$data['jatuh_tempo'].",".$data['status'];

}else{
	echo "failed";
}




	 
?>