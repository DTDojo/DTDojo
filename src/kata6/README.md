State Machine Kata
----------------------

Build a state machine engine that takes three strings as constructor parameters:

   1. a comma-separated list of symbols which make up the input alphabet (example:
   2. a comma-separated list of state:state-value pairs
   3. a comma-separated list of transitions consisting of colon-separated triplets identifying initial state, final state and the symbol that causes that transition. Assume that for inputs which do not cause a state change no transition needs to be defined.

The class should also have an eval method that takes a sequence of input values and returns the value associated with the final state of the machine.

For example, suppose we want to have a state machine to accept only binary-digits with an even number of consecutive ones. The machine would be defined as:

   * symbols = "0,1"
   * states = "EVEN:pass,ODD:fail,BAD:fail"
   * transitions = "EVEN:ODD:1,ODD:EVEN:1,ODD:BAD:0"

And our test code would look like this:

    fsm = new StateMachine("0,1",  "EVEN:pass,ODD:fail,BAD:fail","EVEN:ODD:1,ODD:EVEN:1,ODD:BAD:0"))
    assertEqual("pass", fsm.execute("00110"))
    fsm.reset() // put it back into initial state
    assertEqual("fail", fsm.execute("00111"))
    fsm.reset()
    assertEqual("fail", fsm.execute("001110000110011")