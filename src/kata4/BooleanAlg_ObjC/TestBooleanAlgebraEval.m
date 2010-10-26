//
//  TestBooleanAlgebraEval.m
//  BooleanCalculator
//
//  Created by Neal Wiggins on 10/23/10.
//

#import <SenTestingKit/SenTestingKit.h>
#import "BooleanAlgebraEval.h"



@interface TestBooleanAlgebraEval : SenTestCase {
    BooleanAlgebraEval *evaluator;
}
@end



@implementation TestBooleanAlgebraEval
- (void) setUp {
    evaluator = [BooleanAlgebraEval new];
}
- (void) tearDown{
    [evaluator release];
}

//eval("0") returns 0
- (void) testSimpleZero{
    NSString *value = [evaluator eval:@"0"];
    STAssertTrue([value isEqualToString:@"0"],value);
}
//eval("!0") returns 1
- (void) testSimpleNotZero{
    NSString *value = [evaluator eval:@"!0"];
    STAssertTrue([value isEqualToString:@"1"],value);
}
//eval("0 + 1") returns 1
- (void) testSimpleOneOrZero{
    NSString *value = [evaluator eval:@"0 + 1"];
    STAssertTrue([value isEqualToString:@"1"],value);
}
//eval("0 * 1 * 1") returns 0
- (void) testZeroAndOneAndOne{
    NSString *value = [evaluator eval:@"0 * 1 * 1"];
    STAssertTrue([value isEqualToString:@"0"],value);
}
//val( "!0 * 0 + 1") returns 1 or 0 depending on whether you choose to implement it right or left associative
- (void) testNotZeroAndZeroOrOne{
    NSString *value = [evaluator eval:@"!0 * 0 + 1"];
    STAssertTrue([value isEqualToString:@"1"],value);
}
//eval(" (0+1) * (1+0)") returns 1
- (void) testZeroOrOneAndOneOrZero{
    NSString *value = [evaluator eval:@" (0+1) * (1+0)"];
    STAssertTrue(value!=nil, @"got a nil value");
    STAssertTrue([value isEqualToString:@"1"],value);
}
- (void) testNestedZeroOrOneAndOneOrZero{
    NSString *value = [evaluator eval:@"((0+1)) * ((1+0))"];
    STAssertTrue(value!=nil, @"got a nil value");
    STAssertTrue([value isEqualToString:@"1"],value);
}
- (void) testUnbalancedParenthesis{
    NSString *value = [evaluator eval:@" )(((0+1)) * ((1+0))"];
    STAssertTrue(value==nil, value);
}
- (void) testUnbalanced2Parenthesis{
    NSString *value = [evaluator eval:@"(0+1*1+0"];
    STAssertTrue(value==nil, value);
}
- (void) testInvalidParenthesis{
    NSString *value = [evaluator eval:@"(0"];
    STAssertTrue(value==nil, value);
}
- (void) testInvalidCloseParenthesis{
    NSString *value = [evaluator eval:@"0*1)+0"];
    STAssertTrue(value==nil, value);
}
- (void) testInvalidNumberPattern{
    NSString *value = [evaluator eval:@"00*1+0"];
    STAssertTrue(value==nil, value);
}
- (void) testInvalidCharacterPattern{
    NSString *value = [evaluator eval:@"1x0"];
    STAssertTrue(value==nil, value);
}
- (void) testOneAndNotZero{
    NSString *value = [evaluator eval:@"1*!0"];
    STAssertTrue([value isEqualToString:@"1"],value);
}
@end
