module.exports = {
  "env": {
    "browser": true,
    "node": true
  },
  "globals": {
    "appTool": true,
    "require": true,
    "$": true,
    "ant": true
  },
   "parser": "vue-eslint-parser",
   "parserOptions": {
    "parser": "babel-eslint",
    "sourceType": "module"
  },
  "rules": {
    "accessor-pairs": "error",
    "array-bracket-spacing": [
      "error",
      "never"
    ],
    "array-callback-return": "error",
    "arrow-body-style": ["error", "always"],
    "arrow-parens": "error",
    "arrow-spacing": "error",
    "block-scoped-var": "off",
    "block-spacing": [
      "error",
      "never"
    ],
    "brace-style": "off",
    "callback-return": "off",
    "camelcase": "off",
    "comma-dangle": [
      "error",
      "never"
    ],
    "comma-spacing": "off",
    "comma-style": [
      "error",
      "last"
    ],
    "complexity": "off",
    "computed-property-spacing": [
      "error",
      "never"
    ],
    "consistent-return": "off",
    "consistent-this": "off",
    "curly": "off",
    "default-case": "off",
    "dot-location": [
      "error",
      "property"
    ],
    "dot-notation": "error",
    "eol-last": "off",
    "eqeqeq": "off",
    "func-names": "off",
    "func-style": "off",
    "generator-star-spacing": "error",
    "global-require": 0,
    "guard-for-in": "off",
    "handle-callback-err": "error",
    "id-blacklist": "error",
    "id-length": "off",
    "id-match": "error",
    "indent": ["off", 2],
    "init-declarations": "off",
    "jsx-quotes": ["error", "prefer-double"],
    "key-spacing": "off",
    "keyword-spacing": "off",
    "linebreak-style": [
      "error",
      "windows"
    ],
    "lines-around-comment": "off",
    "max-depth": "off",
    "max-len": "off",
    "max-lines": "off",
    "max-nested-callbacks": "error",
    "max-params": "off",
    "max-statements": "off",
    "max-statements-per-line": "off",
    "new-parens": "off",
    "new-cap": "off",
    "newline-after-var": "off",
    "newline-before-return": "off",
    "newline-per-chained-call": "off",
    "no-alert": "off",
    "no-array-constructor": "error",
    "no-bitwise": "off",
    "no-caller": "error",
    "no-catch-shadow": "off",
    "no-confusing-arrow": "error",
    'no-console': process.env.NODE_ENV === 'production' ? 'error' : 'off',
    'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off',
    "no-continue": "off",
    "no-div-regex": "error",
    "no-duplicate-imports": "error",
    "no-else-return": "off",
    "no-empty-function": "off",
    "no-eq-null": "off",
    "no-eval": [
      "error", {
        "allowIndirect": true
      }
    ],
    "no-extend-native": "error",
    "no-extra-bind": "error",
    "no-extra-label": "error",
    "no-extra-parens": "off",
    "no-floating-decimal": "off",
    "no-implicit-coercion": [
      "error", {
        "boolean": false,
        "number": false,
        "string": false
      }
    ],
    "no-implicit-globals": "off",
    "no-implied-eval": "error",
    "no-inline-comments": "off",
    "no-inner-declarations": [
      "error",
      "functions"
    ],
    "no-invalid-this": "off",
    "no-iterator": "error",
    "no-label-var": "error",
    "no-labels": "error",
    "no-lone-blocks": "error",
    "no-lonely-if": "off",
    "no-loop-func": "off",
    "no-magic-numbers": "off",
    "no-mixed-operators": "off",
    "no-mixed-requires": "error",
    "no-multi-spaces": "error",
    "no-multi-str": "off",
    "no-multiple-empty-lines": ["error", {"max": 1, "maxEOF": 1}],
    "no-native-reassign": "error",
    "no-negated-condition": "off",
    "no-nested-ternary": "off",
    "no-new": "error",
    "no-new-func": "off",
    "no-new-object": "error",
    "no-new-require": "error",
    "no-new-wrappers": "error",
    "no-octal-escape": "error",
    "no-param-reassign": "off",
    "no-path-concat": "error",
    "no-plusplus": "off",
    "no-process-env": "error",
    "no-process-exit": "error",
    "no-proto": "off",
    "no-prototype-builtins": "off",
    "no-restricted-globals": "error",
    "no-restricted-imports": "error",
    "no-restricted-modules": "error",
    "no-restricted-syntax": ["error", "WithStatement"],
    "no-return-assign": "off",
    "no-script-url": "off",
    "no-self-compare": "off",
    "no-sequences": "off",
    "no-shadow": "off",
    "no-shadow-restricted-names": "off",
    "no-spaced-func": "error",
    "no-sync": "off",
    "no-ternary": "off",
    "no-throw-literal": "error",
    "no-trailing-spaces": "off",
    "no-undef-init": "error",
    "no-undef": "off",
    "no-undefined": "off",
    "no-underscore-dangle": "off",
    "no-unmodified-loop-condition": "error",
    "no-unneeded-ternary": [
      "error", {
        "defaultAssignment": true
      }
    ],
    "no-unsafe-finally": "error",
    "no-unused-expressions": "off",
    "no-unused-vars": "off",
    "no-use-before-define": "off",
    "no-useless-call": "off",
    "no-useless-computed-key": "error",
    "no-useless-concat": "off",
    "no-useless-constructor": "error",
    "no-useless-escape": "off",
    "no-useless-rename": "error",
    "no-var": "off",
    "no-void": "off",
    "no-warning-comments": "off",
    "no-whitespace-before-property": "error",
    "no-with": "error",
    "object-curly-newline": "off",
    "object-curly-spacing": ["error", "always"],
    "object-property-newline": [
      "error", {
        "allowMultiplePropertiesPerLine": true
      }
    ],
    "object-shorthand": "off",
    "one-var": "off",
    "one-var-declaration-per-line": "off",
    "operator-assignment": "off",
    "operator-linebreak": [
      "error",
      "after"
    ],
    "padded-blocks": "off",
    "prefer-arrow-callback": "off",
    "prefer-const": "error",
    "prefer-reflect": "off",
    "prefer-rest-params": "off",
    "prefer-spread": "off",
    "prefer-template": "off",
    "quote-props": "off",
    "quotes": [0, "single"],
    "radix": "off",
    "require-jsdoc": "off",
    "require-yield": "error",
    "rest-spread-spacing": "error",
    "semi": ["error", "always"],
    "semi-spacing": "off",
    "sort-imports": "off",
    "sort-vars": "off",
    "space-before-blocks": "off",
    "space-before-function-paren": 0,
    "space-in-parens": "off",
    "space-infix-ops": "off",
    "space-unary-ops": [
      "error", {
        "nonwords": false,
        "words": false
      }
    ],
    "strict": "off",
    "template-curly-spacing": "error",
    "unicode-bom": [
      "error",
      "never"
    ],
    "valid-jsdoc": "off",
    "vars-on-top": "off",
    "wrap-iife": "off",
    "wrap-regex": "off",
    "yield-star-spacing": "error",
    "yoda": "off",
    "react/sort-comp": "off",
    "react/prefer-stateless-function": "off",
    "spaced-comment": ["error", "always"],
    "react/jsx-no-bind": "off",
    "no-extra-boolean-cast": "off"
  }
};
