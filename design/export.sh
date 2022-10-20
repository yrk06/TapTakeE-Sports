cd $1
REACT_PATH=$1/../../frontend/src

for i in *.html 
do
	html2react $i
done

rm -rf *.html

rm -rf $REACT_PATH/components
mv components $REACT_PATH/components
mv assets $REACT_PATH/../public/assets
