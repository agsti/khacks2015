<?php

function load_lyrics() {
var_dump($argv);
	if (!isset($argv[1])) die('Needs lyrics file');
	$lrc_file = file_read_contents($argv[1]);
}

load_lyrics();
