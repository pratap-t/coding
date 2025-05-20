sudo apt update
sudo apt upgrade
curl -fsSL https://deb.nodesource.com/setup_lts.x | sudo -E bash -
sudo apt install -y nodejs
npm install axios react-router-dom
npm audit fix --force
npm install babel-loader --save-dev

# npx create-react-app my-app