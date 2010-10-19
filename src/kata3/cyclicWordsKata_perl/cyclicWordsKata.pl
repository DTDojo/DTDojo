#!/usr/bin/perl
$input=$ARGV[0];
print "Input: $input\n";
@arrays = split(',', $input);
for($i=0;$i <= $#arrays;$i++)
{
    $element = $arrays[$i];
    $element =~ s/^\s+//;
    $element =~ s/\s+$//;
    
    $length = length($element);
    push @{$buckets{$length}}, $element;
}
print "Output:\n";
for my $key ( keys %buckets ) {
        my @elements = @{$buckets{$key}};
        while ( $#elements >= 0 )
        {
            my $matched = findCyclic(@elements);

            print "[" . $matched . "]";
            @elements = @unmatched;
        }
}
print "\n\n\n";

sub findCyclic()
{
    my @input = @_;
    my $matched = $input[0];
    my @unmatch;
    my @return_value ;
    @unmatched = ();
    if($#input == 0 ){
        #@return_value = ();
    }
    else 
    {
        my $full_string = @input[0] . @input[0];
        for ($i=1; $i <= $#input;$i++)
        {
            if (index($full_string, $input[$i]) == 0 )
            {
                 ##same as first element, skip
            }
            elsif (index($full_string, $input[$i]) > 0)
            {
                ###cyclic word of first word
                $matched .= ",$input[$i]"; 
        
            }
            else
            {
                ###not cyclic word of first element
                push @unmatched, $input[$i];
            }
        }
    }
    return $matched;
    #return (\$matched, \@unmatched);
}
