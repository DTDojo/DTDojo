'''
Created on Nov 5, 2010

@author: nwiggins
'''
import unittest
from finite_state_machine import FiniteStateMachine

class Test(unittest.TestCase):


    def testBaseCase(self):
        fsm = FiniteStateMachine("0,1",  "EVEN:pass,ODD:fail,BAD:fail",  "EVEN:ODD:1,ODD:EVEN:1,ODD:BAD:0")
        assert fsm.execute("00110") == 'pass'
        fsm.reset() # put it back into initial state
        assert "fail" == fsm.execute("00111")
        fsm.reset()
        assert "fail" == fsm.execute("001110000110011")
        pass

    def testInvalidInput(self):
        fsm = FiniteStateMachine("0,1",  "EVEN:pass,ODD:fail,BAD:fail",  "EVEN:ODD:1,ODD:EVEN:1,ODD:BAD:0")
        didFail = False
        try:
            fsm.execute("00a10")
        except Exception:
            didFail = True
            pass
        assert didFail
    def testInvalidStateDef(self):
        didFail = False
        try:
            FiniteStateMachine("0,1",  "EVEN,ODD:fail,BAD:fail",  "EVEN:ODD:1,ODD:EVEN:1,ODD:BAD:0")
        except Exception:
            didFail = True
        assert didFail       
    def testInvalidTransDef(self):
        didFail = False
        try:
            FiniteStateMachine("0,1",  "EVEN:pass,ODD:fail,BAD:fail",  "EVEN:1,ODD:EVEN:1,ODD:BAD:0")
        except Exception:
            didFail = True
        assert didFail       

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()