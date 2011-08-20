-module(josephus).
-export([benchmark/0]).
-compile(native).

shout(Count,Nth) ->
    shoutList(lists:seq(1,Count),Nth,1).

shoutList([Head|[]],_,_) -> Head;
shoutList(List,N,Counter) ->
    {FilteredList, NextCounter} = shoutSubList(List,N,Counter,[]),
    shoutList(FilteredList,N, NextCounter).

shoutSubList([],_,Counter,Acc) -> {lists:reverse(Acc),Counter};
shoutSubList([_Head|Tail], N, 1, Acc) -> shoutSubList(Tail, N, 2, Acc);
shoutSubList([Head|Tail], N, Counter,Acc) ->
    NextCounter = 
        if
            Counter =:= N -> 1;
            true -> Counter + 1
        end,
    shoutSubList(Tail,N, NextCounter, [Head | Acc]).

run_iterations(0) -> ok;
run_iterations(Iterations) ->
    shout(40,3),
    run_iterations(Iterations - 1).

run_times(_,0) -> ok;
run_times(Iterations, Times) ->
    Start = os:timestamp(),
    run_iterations(Iterations),
    End = os:timestamp(),
    io:format("~w~n",[timer:now_diff(End,Start) / Iterations]),
    run_times(Iterations,Times-1).

benchmark() ->
    Iter = 1000000,
    io:format("(list reduction)~n",[]),
    run_times(Iter,10).
