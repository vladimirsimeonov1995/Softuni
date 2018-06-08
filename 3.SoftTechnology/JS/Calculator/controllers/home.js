const Calculator = require('./../models/Calculator');

module.exports = {
    indexGet: (req, res) => {
        res.render('home/index');
    },

    indexPost: (req,res) => {
        let calculatorBody = req.body;

        let calculatorParams = calculatorBody['calculator'];

        let calculator = new Calculator();
        calculator.leftOperand = Number(calculatorParams.leftOperand);
        calculator.operator = calculatorParams.operator;
        calculator.rightOperand = Number(calculatorParams.rightOperand);
        calculator.operator2 = calculatorParams.operator2;
        calculator.firstOperand = Number(calculatorParams.firstOperand);
        if(calculatorParams.systemOne == "hexa") {
            calculator.secondOperand = calculatorParams.secondOperand;
        }
        else
            calculator.secondOperand = Number(calculatorParams.secondOperand);
        calculator.systemOne = calculatorParams.systemOne;
        calculator.systemTwo = calculatorParams.systemTwo;

        let result = calculator.calculateResult();
        let result2 = calculator.calculateResult2();
        let result3 = calculator.calculateResult3();

        res.render('home/index',{'calculator':calculator,'result':result,'result2':result2,'result3':result3});
    },


};