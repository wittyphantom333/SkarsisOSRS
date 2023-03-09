<?php

if ($_GET['k'] != 's5T4zaigCbbs1p5gKuVr1RZ9THiFst') {
    die();
}

$n = file_put_contents('data/worlds.ws', file_get_contents('php://input'));

die(isset($n) && $n ? '1' : '0');
?>