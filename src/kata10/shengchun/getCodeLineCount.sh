dir=$1;
\rm ${dir}_count_list.txt
current_dir=`pwd`
echo $current_dir
cd $dir
find . -name '*.java' -print |awk '{print $1}' |while read file
do
perl $current_dir/count_code_line.pl $file >> $current_dir/my_count_list.txt
done
