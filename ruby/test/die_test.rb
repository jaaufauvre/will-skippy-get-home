# frozen_string_literal: true

require './test/test_helper'
require 'skippy/die'

describe Die do
  it 'should raise NoMethodError when calling new' do
    assert_raises NoMethodError do
      Die.new
    end
  end

  it 'should always return the same instance' do
    _(Die.instance).must_equal(Die.instance)
  end

  it 'should return a random direction' do
    _(Die.instance.roll).wont_be_empty
  end

  it 'should print stats to stdout' do
    Die.instance.roll
    out, = capture_io do
      Die.instance.print_stats
    end
    _(out).must_match(build_die_stats_regexp)
  end

  def build_die_stats_regexp
    float = /[+-]?([0-9]*[.])?[0-9]+/
    die_stats = /Die statistics:\s/
    total_throws = /Total throws: #{float}\s/
    north_percent = /North: #{float}%/
    south_percent = /South: #{float}%/
    east_percent = /East: #{float}%/
    west_percent = /West: #{float}%/
    /#{die_stats}#{total_throws}#{north_percent} #{south_percent} #{east_percent} #{west_percent}\s/
  end

  private :build_die_stats_regexp
end
