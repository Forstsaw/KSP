<?php 
	require 'conn.php';
	$tanggal = date("Y/m/d"); 

	 $user_id = $_POST['user_id'];
	 $no_tagihan = $_POST['no_tagihan'];
	 $telah_dibayar = $_POST['telah_dibayar'];
	 $sejumlah = $_POST['sejumlah'];
	 $penerima = $_POST['penerima'];

/*
	 $user_id = "7";
	 $no_tagihan = "VS12321";
	 $telah_dibayar = "1000";
	 $sejumlah = "100";
	 $penerima = "pt vinson";

*/

	$query_balance="SELECT * FROM balance where user_id = '$user_id'";
    $check_balance=mysqli_query($conn,$query_balance) or die(mysqli_error($conn));
    if($check_balance){
    	$row=mysqli_fetch_assoc($check_balance);
    	if((int)$row['balance'] >= (int)$sejumlah){
    		$query="INSERT into angsur(user_id,no_tagihan,telah_dibayar,sejumlah,penerima,tanggal) values ('$user_id','$no_tagihan','$telah_dibayar','$sejumlah','$penerima','$tanggal')";
    		$result=mysqli_query($conn,$query) or die(mysqli_error($conn));
    		   if($result) 
				{
					$sisabalance = $row['balance']-$sejumlah;
					$update_balance="UPDATE balance SET balance = '$sisabalance' WHERE user_id = '$user_id'";
    				$result_balance=mysqli_query($conn,$update_balance) or die(mysqli_error($conn));
    				if($result_balance){
    					echo "success"; 
    				}else{
    					echo "failed"; //gagal insert
    					$sisabalance = ($sejumlah-$row['balance']) + $row['balance'];
    					$update_balance="UPDATE balance SET balance = '$sisabalance' WHERE user_id = '$user_id'";
    					$result_balance=mysqli_query($conn,$update_balance) or die(mysqli_error($conn));

    				}


				}else{
					echo "failed"; //gagal insert

				}

    	}else{
    		echo "tidakcukup"; // deposit dibawah jumlahnya
    	}
    }else{
    	echo "tidakcukup"; //belum pernah di topup
    }


 	


 

?>