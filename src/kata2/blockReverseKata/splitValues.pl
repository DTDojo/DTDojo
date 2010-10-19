#!/usr/bin/perl 

@input = split(',', $ARGV[0]);
$arrayIndex = 0;
$subIndex = 0;
print "$ARGV[0]\n";
while ($a=shift(@input))
{
    if($a == -1 )
    {
        if($array[$arrayIndex])
        {
            $array[++$arrayIndex][0] = $a;
            $arrayIndex++;
            $subIndex = 0;
        }
        else
        {
            $array[$arrayIndex++][0] = $a;
            $subIndex = 0;
        }
    }
    else
    {
        $array[$arrayIndex][$subIndex++] = $a;
    }
}
$start = 1;
@reverseArray;
for($i=$#array;$i >=0; $i--)
{
    @newArray = @{$array[$i]};
    if($start++ > 1)
    {
        #print ",";
        
    }
    #print $element;
    push @reverseArray,@newArray;
}

$element= join(',', @reverseArray);
#print "@reverseArray\n";
print "$element\n\n";
