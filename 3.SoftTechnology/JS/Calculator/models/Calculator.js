/**
 * @return {string}
 */
function Calculator(leftOperand,operator,rightOperand,firstOperand,operator2,secondOperand,systemOne,systemTwo) {
    this.leftOperand = leftOperand;
    this.operator = operator;
    this.rightOperand = rightOperand;
    this.firstOperand = firstOperand;
    this.operator2 = operator2;
    this.secondOperand = secondOperand;
    this.systemOne = systemOne;
    this.systemTwo = systemTwo;



    this.calculateResult = function () {
        let result = 0;
        switch (this.operator) {
            case "+":
                result = this.leftOperand + this.rightOperand;
                break;
            case "-":
                result = this.leftOperand - this.rightOperand;
                break;
            case "*":
                result = this.leftOperand * this.rightOperand;
                break;
            case "/":
                result = this.leftOperand / this.rightOperand;
                break;
            case "% of":
                result = this.rightOperand*this.leftOperand/100;
                break;
            case "%":
                result = this.leftOperand%this.rightOperand;
                break;
            case "< = >":
                if(this.leftOperand > this.rightOperand)
                    result = this.leftOperand + "  >  " + this.rightOperand;
                else if(this.leftOperand < this.rightOperand)
                    result = this.leftOperand + "  <  " + this.rightOperand;
                else if(this.leftOperand === this.rightOperand)
                    result = this.leftOperand + "  =  " + this.rightOperand;
                break;
            case "^":
                result = Math.pow(this.leftOperand,this.rightOperand);
                break;
        }
        if(isNaN(this.leftOperand))
            this.leftOperand="This is not a Number";
        if(isNaN(this.rightOperand))
            this.rightOperand="This is not a Number";
        return result;
    }

    this.calculateResult2 =  function () {
        let result2 = 0;
        switch (this.operator2){
            case "1/x":
                result2 ="1/" + this.firstOperand + "=" + (1/this.firstOperand);
                break;
            case "sqrt":
                result2 ="sqrt(" + this.firstOperand + ") = " + Math.sqrt(this.firstOperand);
                break;
            case "x!":
                let fac=1;
                for (let i = 1; i <= this.firstOperand; i++) {
                    fac*=i;
                }
                result2 = this.firstOperand + "! = " + fac;
                break;
            case "sin(x)":
                result2 = "sin(" + this.firstOperand + "rad) = " + Math.sin(this.firstOperand);
                break;
            case "cos(x)":
                result2 = "cos(" + this.firstOperand + "rad) = " + Math.cos(this.firstOperand);
                break;
            case "log(x)":
                result2 = "log(" + this.firstOperand + ") = " + Math.log(this.firstOperand);
                break;
            case "ex":
                result2 = "e^" + this.firstOperand + " = " + Math.pow(Math.E,this.firstOperand);
                break;
            case "|x|":
                result2 = "|" + this.firstOperand + "| = " + Math.abs(this.firstOperand);
        }


        return result2;


    }

    this.calculateResult3 = function () {
        let result3  = 0;


        if(this.systemOne === "binary" && this.systemTwo === "binary")
            result3 = this.secondOperand;
        else if(this.systemOne === "binary" && this.systemTwo === "dec")
            result3 = parseInt(this.secondOperand, 2);
        else if(this.systemOne === "binary" && this.systemTwo === "octal") {
            result3 = parseInt(this.secondOperand, 2);
            console.log(result3);
            result3 = result3.toString(8);
        }
        else if(this.systemOne === "binary" && this.systemTwo === "hexa") {
            result3 = parseInt(this.secondOperand, 2);
            result3 = result3.toString(16).toUpperCase();
        }



        else if(this.systemOne === "dec" && this.systemTwo === "dec")
            result3 = this.secondOperand;
        else if(this.systemOne === "dec" && this.systemTwo === "binary") {
            result3 = this.secondOperand;
            result3 = result3.toString(2);
        }
        else if(this.systemOne === "dec" && this.systemTwo === "octal") {
            result3 = this.secondOperand;
            result3 = result3.toString(8);
        }
        else if(this.systemOne === "dec" && this.systemTwo === "hexa") {
            result3 = this.secondOperand;
            result3 = result3.toString(16);
        }



        else if(this.systemOne === "octal" && this.systemTwo === "octal")
            result3 = this.secondOperand;
        else if(this.systemOne === "octal" && this.systemTwo === "binary"){
            result3 = parseInt(this.secondOperand, 8);
            console.log(result3);
            result3 = result3.toString(2);
        }
        else if(this.systemOne === "octal" && this.systemTwo === "dec")
            result3 = parseInt(this.secondOperand, 8);
        else if(this.systemOne === "octal" && this.systemTwo === "hexa"){
            result3 = parseInt(this.secondOperand, 8);
            result3 = result3.toString(16).toUpperCase();
        }


        if(this.systemOne === "hexa" && this.systemTwo === "hexa") {
            result3 = this.secondOperand.toUpperCase();

        }
        if(this.systemOne === "hexa" && this.systemTwo === "binary"){
            result3 = parseInt(this.secondOperand, 16);
            result3 = result3.toString(2);
        }
        else if(this.systemOne === "hexa" && this.systemTwo === "dec")
            result3 = parseInt(this.secondOperand, 16);
        else if(this.systemOne === "hexa" && this.systemTwo === "octal"){
            result3 = parseInt(this.secondOperand, 16);
            result3 = result3.toString(8);
        }

        return result3;

    }

}

module.exports = Calculator;