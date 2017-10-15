const path = require('path');
const CleanWebpackPlugin = require('clean-webpack-plugin');


module.exports = {
    entry: {
        hello: './src/main/resources/src/scripts/index.js',
        print: './src/main/resources/src/scripts/print.js'
    },
    devtool: 'inline-source-map',
    plugins: [
      new CleanWebpackPlugin(['src/main/resources/static/scripts'])
    ],
    output: {
        filename: '[name].bundle.js',
        path: path.resolve(__dirname, 'src/main/resources/static/scripts')
    },
    module: {
        rules: [
            {
                test: /\.css$/,
                use: [
                    'style-loader',
                    'css-loader'
                ]
            }
        ]
    }
};
