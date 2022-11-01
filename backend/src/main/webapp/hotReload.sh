rm -rf build/

npm run build

rm -rf ../../../target/classes/static
mv build ../../../target/classes/static