#!/usr/bin/perl

$input_file = $ARGV[0];

open FILE, $input_file;
$isMultilineComment = 0;
$line_count = 0;
while(<FILE>)
{
    $line = $_;
    if($line =~ /^\s*$/){
        ##blank line/white space only, skip
        next;
    }
    if($line =~ /^\s*\/\/.*$/){
        ##single comment line, skip
        next;
    }
    if($line =~ /^\s*[\{\}]\s*(\/\/.*)?$/ ){
        ##only '{' or '}', skip
        next;
}
    if($line =~ /^\s*\{\s*\}\s*$/ ){
        ##only '{' or '}', skip
        next;
    }
    if($line =~ /^\s*\/\*.*\*\/\s*/ ){
        ### /*  start and end of multiline comment
        $isMultilineComment = 0;
        next;
    }
    if($line =~ /^\s*\/\*/ ){
        ### /*  start of multiline comment
        $isMultilineComment = 1;
        next;
    }
    if($isMultilineComment == 1){
        if($line =~ /\*\/\s*$/ ){
            ##end multiline comment with */
            $isMultilineComment = 0;
        }
        next;
    }
    else
    {
        $line_count++;
        ##print "$line\n";
    }


}

print "$input_file:$line_count\n";

exit 0;
