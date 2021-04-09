<?php 

	require 'conn.php';
	$user_id = $_POST["id"];
	//$user_id = "7";

	//SIMPAN SQL
	$sql_simpan="SELECT * FROM simpanan where user_id = '$user_id' ORDER BY simpan_id DESC";
	$simpan_hasil=mysqli_query($conn,$sql_simpan) or die(mysqli_error($conn));
	if (mysqli_num_rows($simpan_hasil) > 0) {
		while($data_simpan = mysqli_fetch_assoc($simpan_hasil)){
			echo "simpan,".$data_simpan['simpan_id'].",".number_format($data_simpan['balance'],2,',','.').",".$data_simpan['tanggal_simpan'].",end,";
		}
	}else{
		"kosong,Tidak Ada Data";
	}

	//PINJAM SQL
	$sql_pinjam="SELECT * FROM pinjaman where user_id = '$user_id' ORDER BY pinjam_id DESC";
	$hasil_pinjam=mysqli_query($conn,$sql_pinjam) or die(mysqli_error($conn));
	if (mysqli_num_rows($hasil_pinjam) > 0) {
		while($data_pinjam = mysqli_fetch_assoc($hasil_pinjam)){
			echo "pinjam,".$data_pinjam['pinjam_id'].",".number_format($data_pinjam['balance'],2,',','.').",".$data_pinjam['waktu_peminjaman'].",end,";
		}
	}else{
		"kosong,Tidak Ada Data";
	}

	//ANGSUR SQL
	$sql_angsur="SELECT * FROM angsur where user_id = '$user_id' ORDER BY angsur_id DESC";
	$hasil_angsur=mysqli_query($conn,$sql_angsur) or die(mysqli_error($conn));
	if (mysqli_num_rows($hasil_angsur) > 0) {
		while($data_angsur = mysqli_fetch_assoc($hasil_angsur)){
			echo "angsur,".$data_angsur['angsur_id'].",".$data_angsur['no_tagihan'].",".number_format($data_angsur['sejumlah'],2,',','.').",".$data_angsur['tanggal'].",end,";
		}
	}else{
		"kosong,Tidak Ada Data";
	}


	
 ?>