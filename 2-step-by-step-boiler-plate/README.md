# Creating a React Development Environment from Scratch

We are going in this section to create a JavaScript development environment for React from zero to a complete viable setup. This development environment will include packaging, code quality and compilation tools in addition to the React library.

- [Creating a React Development Environment from Scratch](#creating-a-react-development-environment-from-scratch)
  - [Motivation](#motivation)
  - [Prerequisites](#prerequisites)
  - [Environment Setup](#environment-setup)
    - [Project Directory](#project-directory)
    - [Webpack 4](#webpack-4)
    - [Babel 7](#babel-7)
    - [ESlint](#eslint)
    - [Prettier](#prettier)
    - [React](#react)
    - [Visual Studio Code](#visual-studio-code)
    - [Git](#git)
  - [Testing the Setup](#testing-the-setup)
    - [Scripting Setup](#scripting-setup)
    - [React Code](#react-code)
    - [Building and Running the Example](#building-and-running-the-example)
  - [React Developer Tool Browser Extension](#react-developer-tool-browser-extension)
  - [What's Next](#whats-next)

## Motivation
Although automatic tools for creating a complete seed for React development environment exists, we still need; for the purpose of learning; to create manually our development environment in order to understand the React and JavaScript ecosystem and be able to customize the delivery of our final application.

**[:arrow_double_up: Steps](#creating-a-react-development-environment-from-scratch)**

## Prerequisites
Before we start, make sure that you have the following programs:
- the latest version of [_node_](https://nodejs.org), a _runtime environment for JavaScript_ that allows to execute JavaScript programs outside of the browser, you can verify this by using the command
``` Bash
node -v
```
- [_Visual Studio Code_](https://code.visualstudio.com/), our code editor for choice
- [git](https://git-scm.com/downloads), verify your git setup via this command
  ``` Bash
  git --version
  ```

**[:arrow_double_up: Steps](#creating-a-react-development-environment-from-scratch)**

## Environment Setup

### Project Directory
Create and move into your development directory
``` Bash
mkdir react-boiler-plate
cd react-boiler-plate
```
and execute the command
``` Bash
npm init --yes
```
this will create a file named _package.json_ where the project dependencies and commands (like build and run) will be specified.  
Inside the directory, create 3 sub-folders
``` Bash
mkdir scr
mkdir dist
mkdir config
```
the _src_ directory will contain the code for our components, the _dist_ is for the packaged resulting application (more details in the next section) and the _config_ directory is for configuration files of the other tools.

**[:arrow_double_up: Steps](#creating-a-react-development-environment-from-scratch)**

### Webpack 4
We use [_Webpack_](https://webpack.js.org/) to build our JavaScript application, Webpack creates a single _bundle_ that contains all our front end code and can minify and obstruct it for production deliverables. We'll install all the dependencies of Webpack for our boilerplate  
``` Bash
npm install --save-dev webpack webpack-cli webpack-dev-server webpack-merge \
                    html-webpack-plugin \
                    clean-webpack-plugin \
                    img-loader url-loader file-loader
```
Once installed, we have to configure Webpack for out project. The configuration for Webpack will be contained inside files under the _config_ directory.
``` Bash
mkdir ./config/webpack
```
Under the the root directory of the project, create a file named _webpack.config.js_
``` Bash
touch webpack.config.js
```
and put the following content inside
``` JavaScript
require('@babel/register');
const webpackMerge = require('webpack-merge');

const common = require('./config/webpack/webpack.common');

const envs = {
    development: 'dev',
    production: 'prod'
};

const env = envs[process.env.NODE_ENV || 'development'];
const envConfig = require(`./config/webpack/webpack.${env}`);
module.exports = webpackMerge(common, envConfig);
```
This file is the entry point for Webpack to select the mode (either development of production) and it depends on other configuration files that we'll create inside the _config/webpack_ directory as the following
``` Bash
touch ./config/webpack/paths.js
touch ./config/webpack/rules.js
touch ./config/webpack/webpack.common.js
touch ./config/webpack/webpack.dev.js
touch ./config/webpack/webpack.prod.js
```
the file _paths.js_ is used to instruct Webpack on the paths of files to bundle for the build such as where to put the resulting bundled application or where to find sources
``` JavaScript
import path from 'path';

module.exports = {
    root: path.resolve(__dirname, '../', '../'),
    outputPath: path.resolve(__dirname, '../', '../', 'dist'),
    entryPath: path.resolve(__dirname, '../', '../', 'src/index.js'),
    templatePath: path.resolve(__dirname, '../', '../', 'index.html'),
    imagesFolder: 'images',
    fontsFolder: 'fonts',
    cssFolder: 'css',
    jsFolder: 'js'
};
```
the file _rules.js_ is for indicating how to load the input source files to produce the bundle
``` JavaScript
module.exports = [
    {
        test: /\.js$/,
        exclude: /node_modules/,
        use: {
            loader: 'babel-loader'
        }
    },
    {
        test: /\.eot(\?v=\d+\.\d+\.\d+)?$/,
        exclude: /node_modules/,
        loader: 'file-loader'
    },
    {
        test: /\.(woff|woff2)$/,
        exclude: /node_modules/,
        loader: 'url-loader?prefix=font/&limit=5000'
    },
    {
        test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/,
        exclude: /node_modules/,
        loader: 'url-loader?limit=10000&mimetype=application/octet-stream'
    },
    {
        test: /\.(jpe?g|png|gif|svg)$/i,
        use: ['url-loader?limit=10000', 'img-loader']
    }
];
```
the file _webpack.common.js_ is for common Webpack configurations
``` JavaScript
import webpack from 'webpack';
import HtmlWebpackPlugin from 'html-webpack-plugin';
import paths from './paths';
import rules from './rules';

module.exports = {
    entry: paths.entryPath,
    module: {
        rules
    },
    resolve: {
        modules: ['src', 'node_modules'],
        extensions: ['*', '.js', '.scss', '.css']
    },
    plugins: [
        new webpack.ProgressPlugin(),
        new HtmlWebpackPlugin({
            template: paths.templatePath,
            minify: {
                collapseInlineTagWhitespace: true,
                collapseWhitespace: true,
                preserveLineBreaks: true,
                minifyURLs: true,
                removeComments: true,
                removeAttributeQuotes: true
            }
        })
    ]
};
```
finally, the files _webpack.dev.js_ and _webpack.prod.js_ are respectively for development and production configuration
``` JavaScript
import webpack from 'webpack';
import paths from './paths';
import rules from './rules';

module.exports = {
    mode: 'development',
    output: {
        filename: '[name].js',
        path: paths.outputPath,
        chunkFilename: '[name].js'
    },
    module: {
        rules
    },
    performance: {
        hints: 'warning',
        maxAssetSize: 450000,
        maxEntrypointSize: 8500000,
        assetFilter: assetFilename => {
            return (
                assetFilename.endsWith('.css') || assetFilename.endsWith('.js')
            );
        }
    },
    optimization: {
        splitChunks: {
            chunks: 'all'
        }
    },
    devServer: {
        contentBase: paths.outputPath,
        compress: true,
        hot: true,
        historyApiFallback: true
    },
    plugins: [
        new webpack.HotModuleReplacementPlugin()
    ]
};
```
``` JavaScript
import CleanWebpackPlugin from 'clean-webpack-plugin';
import paths from './paths';
import rules from './rules';

module.exports = {
    mode: 'production',
    output: {
        filename: `${paths.jsFolder}/[name].[hash].js`,
        path: paths.outputPath,
        chunkFilename: '[name].[chunkhash].js'
    },
    module: {
        rules
    },
    plugins: [
        new CleanWebpackPlugin([paths.outputPath.split('/').pop()], {
            root: paths.root
        })
    ],
    devtool: 'source-map'
};
```

**[:arrow_double_up: Steps](#creating-a-react-development-environment-from-scratch)**

### Babel 7
The compilation of our React code is base on the _compiler [Babel](https://babeljs.io/)_ that transforms our ES6/JSX code into plan JavaScript compatible with any browser (even IE, by using the [_polyfill_](https://babeljs.io/docs/en/6.26.3/babel-polyfill) plugin).  
To install Babel, run the following command
``` Bash
npm install --save-dev @babel/core @babel/cli @babel/node @babel/plugin-proposal-class-properties \
                    @babel/plugin-proposal-object-rest-spread @babel/plugin-syntax-dynamic-import \
                    @babel/plugin-syntax-import-meta @babel/plugin-transform-async-to-generator \
                    @babel/plugin-transform-runtime @babel/preset-env @babel/preset-react \
                    @babel/register @babel/runtime \
                    babel-eslint babel-jest babel-loader babel-core@7.0.0-bridge.0
```
We have to create a file named _.babelrc_ to configure our Babel configuration
``` Bash
touch .babelrc
```
and add the following content
``` JavaScript
{
    "presets": [
        [
            "@babel/preset-env",
            {
                "useBuiltIns": "entry"
            }
        ],
        "@babel/preset-react"
    ],
    "plugins": [
        "@babel/plugin-proposal-object-rest-spread",
        "@babel/plugin-transform-runtime",
        "@babel/plugin-transform-async-to-generator",
        "@babel/plugin-proposal-class-properties"
    ]
}
```

**[:arrow_double_up: Steps](#creating-a-react-development-environment-from-scratch)**

### ESlint
In addition to compiling and building, our project needs static source quality control to find potential bugs and make the code cleaner. For JavaScript, the most popular tool for _linting_ is [_ESLint_](https://eslint.org/). To setup ESLint run the following command
``` Bash
npm install --save-dev eslint eslint-config-airbnb \
                    eslint-config-prettier eslint-loader \
                    eslint-plugin-babel \
                    eslint-plugin-import \
                    eslint-plugin-jsx-a11y \
                    eslint-plugin-prettier \
                    eslint-plugin-react
```
We need now to configure the linter; so create a file called _.eslintrc_
``` Bash
touch .eslintrc
```
and copy the following content inside
``` JavaScript
{
    "parser": "babel-eslint",
    "extends": ["airbnb", "prettier", "prettier/react"],
    "plugins": ["prettier"],
    "parserOptions": {
        "ecmaVersion": 6,
        "ecmaFeatures": {
            "jsx": true
        }
    },
    "env": {
        "browser": true,
        "node": true,
        "mocha": true,
        "es6": true,
        "jest": true
    },
    "rules": {
        "indent": ["error", 4],
        "space-before-function-paren": "off",
        "react/prefer-stateless-function": "warn",
        "react/jsx-one-expression-per-line": "off",
        "import/no-extraneous-dependencies": [
            "error",
            { "devDependencies": true }
        ],
        "react/jsx-filename-extension": [1, { "extensions": [".js", ".jsx"] }],
        "linebreak-style": "off",
        "global-require": "off",
        "semi": "warn",
        "arrow-body-style": "off",
        "no-multiple-empty-lines": ["warn", { "max": 1 }],
        "no-unused-expressions": [
            "error",
            {
                "allowTaggedTemplates": true
            }
        ],
        "no-underscore-dangle": ["error", { "allow": ["__REDUX_DEVTOOLS_EXTENSION_COMPOSE__"] }]
    }
}
```
ESLint scans our source files to fix code quality bugs, but it can scan our imported modules directory _node_modules_ or the resulting bundle created by Webpack, so we have to instruct ESLint to ignore those directories by creating a file called _.eslintignore_
``` Bash
touch .eslintignore
```
and put the following content
``` 
node_modules/
dist/
```

**[:arrow_double_up: Steps](#creating-a-react-development-environment-from-scratch)**

### Prettier
Our JavaScript code needs to be formatted too, so we use [_Prettier_](https://prettier.io/) to auto-format our source files. We install Prettier with this command
``` Bash
npm install --save-dev prettier
```
and the configuration file is _.prettierrc_
``` Bash
touch .prettierrc
```
with the following content
``` JSON
{
    "printWidth": 120,
    "tabWidth": 2,
    "semi": true,
    "singleQuote": true,
    "bracketSpacing": true,
    "trailingComma": "es5"
}
```

**[:arrow_double_up: Steps](#creating-a-react-development-environment-from-scratch)**

### React
Execute the following command to add the React library to the project
``` Bash
npm install --save react react-dom
```

**[:arrow_double_up: Steps](#creating-a-react-development-environment-from-scratch)**

### Visual Studio Code
Make sure that the following plugins are available in your editor:
- [Prettier](https://marketplace.visualstudio.com/items?itemName=esbenp.prettier-vscode)
- [ESLint](https://marketplace.visualstudio.com/items?itemName=dbaeumer.vscode-eslint)
- [Path Intellisense](https://marketplace.visualstudio.com/items?itemName=christian-kohler.path-intellisense)

We'll now configure the activation of code formatting and linting inside VS Code. Type ``` CTRL + , ``` and in the search bar type the following keys:
- _eslint.autoFixOnSave_
- _prettier.eslintIntegration_

and set them both to _true_. Add also a file named _.editorconfig_ a portable editor formatting file with the following content
``` properties
root = true

[*]
indent_style = space
indent_size = 2
end_of_line = lf
charset = utf-8
trim_trailing_whitespace = true
insert_final_newline = true
```

**[:arrow_double_up: Steps](#creating-a-react-development-environment-from-scratch)**

### Git
We are going to tell _git_ to ignore our JavaScript imported libraries and some of the local content via the file _.gitignore_
```
node_modules
package-lock.json
dist
```

:information_source: for advanced setup (out of scope), we can configure _git hooks_ to be executed before committing, take a look at [_Husky_](https://github.com/typicode/husky).

**[:arrow_double_up: Steps](#creating-a-react-development-environment-from-scratch)**

## Testing the Setup
Finally, we finished creating a viable development environment for React, we can now test it by specifying how to build it and run it with a simple code.

### Scripting Setup
Inside the _package.json_ file, we have to specify how to build and run the React example code.
1. specify the entry point by setting the _main_ as the following ```"main": "src/index.js"```
2. put the following content for the _scripts_ attribute
``` JSON
"scripts": {
    "lint": "eslint --ext .js,.jsx src",
    "build:dev": "webpack --mode development",
    "build:prod": "webpack --mode production",
    "start:dev": "webpack-dev-server --progress --colors --mode development",
    "start:prod": "webpack-dev-server --mode production",
    "build": "npm run build:dev",
    "start": "npm run start:dev"
}
```
The attributes are for the commands to be executed by _npm_ like
``` Bash
npm run lint
```
to run code quality scan by lint.

**[:arrow_double_up: Steps](#creating-a-react-development-environment-from-scratch)**

### React Code
At the root directory of the project, add the file _index.html_ with the following content
``` HTML
<!DOCTYPE html>
<html>
    <head>
        <title>React Boilerplate</title>
        <meta charset="UTF-8" />
    </head>

    <body>
        <div id="app"></div>
        <script src="src/index.js"></script>
    </body>
</html>
```
Under the _src_ directory, create the file _index.js_ with the following content
``` jsx
import React from 'react';
import ReactDOM from 'react-dom';

ReactDOM.render(
    <div>Hello React !!!</div>,
    document.getElementById("app")
);
```

:information_source: if the VS Code setup with Prettier and ESLint did go well, you'll notice that it did format the code automatically after saving.

**[:arrow_double_up: Steps](#creating-a-react-development-environment-from-scratch)**

### Building and Running the Example
We have to import the project dependencies, so run first the following command
``` Bash
npm install
```
the build the project (in development mode by default)
``` Bash
npm run build
```
then start the application (also in development mode by default)
``` Bash
npm start
```
for production mode, we have just to run the following commands
``` Bash
npm run build:prod
npm run start:prod
```
If all goes well, the project can be found running at the URL _http://localhost:8080/_

:information_source: try to make changes inside the script _index.js_ or the main page _index.html_ and see how those changes are automatically visible in the application, and this is one of the features of Webpack for the development mode.

**[:arrow_double_up: Steps](#creating-a-react-development-environment-from-scratch)**

## React Developer Tool Browser Extension
It's possible to inspect the React UI components an their state and props on the browser using the plugin [React Developer Tools](https://github.com/facebook/react-devtools). Install this plugin in your browser of choice and build the example application both in development and production mode and see how the output differs and also behavior of the plugin.

:information_source: you can also open existing web sites based on React, namely Facebook.

**[:arrow_double_up: Steps](#creating-a-react-development-environment-from-scratch)**

## What's Next
In the [next section](../3-project/task-manager-gui/#task-manager-gui), we'll not do again the same manual setup, we'll instead automate the environment creation using the command [_create-react-app_](https://github.com/facebook/create-react-app) and develop a typical "useful" React application.

**[:arrow_double_up: Steps](#creating-a-react-development-environment-from-scratch)**
